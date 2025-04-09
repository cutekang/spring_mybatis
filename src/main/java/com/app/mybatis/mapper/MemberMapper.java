package com.app.mybatis.mapper;

import com.app.mybatis.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    public void insert(MemberVO memberVO);

    public Optional<MemberVO> selectMember(MemberVO memberVO);

    public List<MemberVO> selectAll();

    public void updateMember(MemberVO memberVO);

    public void deleteMember(Long id);
}
