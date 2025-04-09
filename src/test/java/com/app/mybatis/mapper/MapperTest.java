package com.app.mybatis.mapper;

import com.app.mybatis.domain.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class MapperTest {

    @Autowired
    private TimeMapper timeMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void getTimeTest(){
        log.info("===========================");
//        log.info("{}", timeMapper.getTime());
        log.info("{}", timeMapper.getTime2());
        log.info("===========================");
    }

    @Test
    public void memberInsertTest(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("ksh1234@gmail.com");
        memberVO.setMemberPassword("1234");
        memberVO.setMemberName("홍길동");
        memberMapper.insert(memberVO);
    }

    @Test
    public void memberSelectTest(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("test001@gmail.com");
        memberVO.setMemberPassword("1234");
        memberVO.setMemberName("홍길동");

        Optional<MemberVO> foundMember = memberMapper.selectMember(memberVO);

        foundMember.ifPresent((MemberVO member) -> {
            log.info("{}", member);
        });

        foundMember.map((MemberVO::toString)).ifPresentOrElse(log::info, ()->{
            new RuntimeException("로그인 실패");
        });
    }

    @Test
    public void memberSelectAllTest(){
        memberMapper.selectAll().forEach((memberVO -> {
            log.info("{}", memberVO);
        }));
    }

    @Test
    public void memberUpdateTest(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("test111@gmail.com");
        memberVO.setMemberPassword("3456");
        memberVO.setMemberName("양진영");
        memberVO.setId(1L);

        memberMapper.updateMember(memberVO);
    }

    @Test
    public void memberDeleteTest(){
        memberMapper.deleteMember(2L);
    }
}
