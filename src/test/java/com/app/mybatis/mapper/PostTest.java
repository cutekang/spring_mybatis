package com.app.mybatis.mapper;

import com.app.mybatis.domain.MemberVO;
import com.app.mybatis.domain.PostDTO;
import com.app.mybatis.domain.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

@SpringBootTest
@Slf4j
public class PostTest {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void insertTest(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("test111@gmail.com");
        memberVO.setMemberPassword("3456");
        memberMapper.selectMember(memberVO)
                .map(MemberVO::getId)
                .ifPresent((Long memberId) -> {
                    PostVO postVO = new PostVO();

                    postVO.setMemberId(memberId);
                    postVO.setPostTitle("테스트 작성글4");
                    postVO.setPostContent("테스트 작성내용4");

                    postMapper.insert(postVO);
                });
    }

    @Test
    public void selectAllTest(){
        postMapper.selectAll().stream().map(PostDTO::toString).forEach(log::info);
    }

    @Test
    public void selectTest(){
        postMapper.select(1L).map(PostDTO::toString).ifPresent(log::info);
    }

    @Test
    public void updateTest(){
        postMapper.select(1L).ifPresent(post -> {
            PostVO postVO = new PostVO();

            postVO.setId(post.getId());
            postVO.setPostTitle("제목 변경해버리기");
            postVO.setPostContent("내용 변경해버리기");
            postVO.setMemberId(post.getMemberId());
            postVO.setPostReadCount(post.getPostReadCount());

            postMapper.update(postVO);
        });
    }

    @Test
    public void deleteTest(){
        postMapper.delete(2L);
    }

//    한 사람이 50개의 글을 작성하도록 코딩
    @Test
    public void insertPostTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("ksh1234@gmail.com");
        memberVO.setMemberPassword("1234");
        memberMapper.selectMember(memberVO)
                .map(MemberVO::getId)
                .ifPresent((Long memberId) -> {
                    for (int i = 0; i < 50; i++) {
                        PostVO postVO = new PostVO();
                        postVO.setMemberId(memberId);
                        postVO.setPostTitle("테스트 작성 글" + (i + 1));
                        postVO.setPostContent("테스트 작성 내용" + (i + 1));
                        postMapper.insert(postVO);
                    }
                });
    }

    @Test
    public void increaseReadCountTest(){
        Random random = new Random();

        for(int i = 0; i < 10000; i++){
            Long id = Long.valueOf(random.nextInt(51));

            postMapper.select(id)
                    .map(PostDTO::getId)
                    .ifPresent(postMapper::increaseReadCount);

//            postMapper.increaseReadCount(id);
        }
    }

//    동적 쿼리 정렬
//    아무것도 전달하지 않을 때
    @Test
    public void selectAllWithOrderTest(){
        String order = null;

        if(order == null){ order = ""; }

        postMapper.selectAllWithOrder(order)
                .stream().map(PostVO::toString).forEach(log::info);
    }

//    동적 쿼리 정렬
//    아무것도 전달하지 않을 때
    @Test
    public void selectAllWithOrderTestPopular(){
        String order = "popular";

        if(order == null){ order = ""; }

        postMapper.selectAllWithOrder(order)
                .stream().map(PostVO::toString).forEach(log::info);
    }

//    postMapper에 동적 쿼리 추가하기
//    만약 order가 "asc" 라면 오름차순으로 정렬하기
    @Test
    public void selectAllWithOrderTestAsc(){
        String order = "asc";

        if(order == null){ order = ""; }

        postMapper.selectAllWithOrder(order)
                .stream().map(PostVO::toString).forEach(log::info);
    }

    @Test
    public void selectWithParamsTest(){
        HashMap<String, Object> params = new HashMap<>();
        params.put("order", "popular");

//        if(params.get("cursor") == null){
//            params.put("cursor", 1);
//        }

        params.put("cursor", 1);
        params.put("direction", "desc");

        postMapper.selectAllWithParams(params)
                .stream().map(PostVO::toString).forEach(log::info);
    }
}
