package com.example.area.areabackend.service.impl;

import com.example.area.areabackend.common.Ret;
import com.example.area.areabackend.common.RetCode;
import com.example.area.areabackend.dao.ScoreDao;
import com.example.area.areabackend.dao.UserDao;
import com.example.area.areabackend.entity.DBInsertUser;
import com.example.area.areabackend.entity.DBQueryScore;
import com.example.area.areabackend.entity.DBSelectUser;
import com.example.area.areabackend.service.IndexService;
import com.example.area.areabackend.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ScoreDao scoreDao;

    private String genToken(Integer userId, int hours) {
        Claims claims = new DefaultClaims();
        claims.setId(userId.toString());
        claims.setIssuedAt(new Date());
        claims.setExpiration(Date.from(LocalDateTime.now().plusHours(hours).atZone(ZoneId.systemDefault()).toInstant()));
        return JwtUtils.encode(claims, Keys.hmacShaKeyFor("01234567890123456789012345678901".getBytes(StandardCharsets.UTF_8)));
    }

    private static Jws<Claims> parseToken(String token) {
        try {
            return JwtUtils.decode(token, Keys.hmacShaKeyFor("01234567890123456789012345678901".getBytes(StandardCharsets.UTF_8)));
        } catch(RuntimeException e) {
            return null;
        }
    }

//    @Transactional
    @Override
    public Ret signUp(String username, String password) {
        // 验证
        if(username.length() > 16 || !username.matches("^[0-9a-zA-Z_]{1,}$") || password.length() != 32) {
            return new Ret(RetCode.PARAMSINVALID);
        }

        // 通过
        DBInsertUser insertUser = new DBInsertUser();
        insertUser.setUsername(username);
        insertUser.setPassword(password);

        //
        try {
            int rows = userDao.insertUser(insertUser);
        } catch(DuplicateKeyException e){
            e.printStackTrace();
            return new Ret(RetCode.DBDUPLICATE);
        } catch(RuntimeException e) {
            e.printStackTrace();
            return new Ret(RetCode.DBERROR);
        }

        // 得到userId
        DBSelectUser selectUser = new DBSelectUser();
        selectUser.setId(insertUser.getId());
        // 得到成绩
        List<DBQueryScore> queryScore = new ArrayList<DBQueryScore>();
        //
        Ret ret = new Ret(RetCode.SUCCESSED);
        ret.info.put("user", selectUser);
        ret.info.put("score", queryScore);
        ret.info.put("token", genToken(selectUser.getId(), 24 * 7));
        return ret;
    }

    @Override
    public Ret signIn(String username, String password, String gameTag) {
        // 验证

        // 通过
        // 得到userId
        DBSelectUser selectUser;
        try {
            selectUser = userDao.selectUser(username, password);
        } catch(RuntimeException e) {
            e.printStackTrace();
            return new Ret(RetCode.DBERROR);
        }

        //
        Ret ret = new Ret(RetCode.SUCCESSED);
        ret.info.put("user", selectUser);

        if(selectUser != null) {
            // 得到成绩
            List<DBQueryScore> queryScore = new ArrayList<DBQueryScore>();
            try {
                queryScore = scoreDao.queryScore(selectUser.getId(), gameTag);
            } catch (RuntimeException e) {
                e.printStackTrace();
                return new Ret(RetCode.DBERROR);
            }
            //
            ret.info.put("score", queryScore);
            ret.info.put("token", genToken(selectUser.getId(), 24 * 7));
            //ret.info.put("refreshToken", GenToken(selectUser.getId(), 24 * 7, "98765432109876543210987654321098"));
        }
        return ret;
    }

    @Override
    public Ret saveScore(String token, String gameTag, int level, int score) {
        // 验证
        Jws<Claims> claims = parseToken(token);
        if(claims == null) {
            // token解析失败
            return new Ret(RetCode.TOKENERROR);
        }

        // 通过
        try {
            int rows = scoreDao.saveScore(Integer.parseInt(claims.getBody().getId()), gameTag, level, score);
        } catch(RuntimeException e) {
            e.printStackTrace();
            return new Ret(RetCode.DBERROR);
        }

        //
        return new Ret(RetCode.SUCCESSED);
    }
}
