package service;

import persistence.dao.GroupDAO;
import persistence.dao.JoinGroupDAO;
import persistence.dao.LocalInfoDAO;
import persistence.dao.MemberDAO;
import persistence.dto.*;

import java.util.List;

public class GroupService {
    private MemberDAO memberDAO;
    private GroupDAO groupDAO;
    private JoinGroupDAO joinGroupDAO;
    private LocalInfoDAO localInfoDAO;
    private JoinGroupDTO joinGroupDTO;
    private GroupDTO groupDTO;
    public GroupService(){
        this.memberDAO = new MemberDAO();
        this.groupDAO = new GroupDAO();
        this.joinGroupDAO = new JoinGroupDAO();
        this.localInfoDAO = new LocalInfoDAO();
    }

    public int isDuplication_id(String name){
        System.out.println("그룹 명 : "+ name);
        List<GroupDTO> list = groupDAO.selectAllByName(name);
        if(list.size() > 0){
            System.out.println("중복 아이디 존재");
            return 0;
        }
        else {
            System.out.println("중복 아이디 없음");
            return 1;
        }
    }

    public void createGroup(PackingDTO packingDTO){
        String nickName = memberDAO.selectOneNickNameById(packingDTO.getMemberDTO().getMemberID());
        int primary_member = memberDAO.selectOneById(packingDTO.getMemberDTO().getMemberID());
        int primary_LocalInfo = localInfoDAO.selectID(packingDTO.getLocalInfoDTO());

        groupDTO.setGroupLeader(nickName);
        groupDTO.setLocalInfoIndex(primary_LocalInfo);
        groupDAO.insertGroup(groupDTO);

        joinGroupDTO.setGroupIndex(groupDTO.getGroupIndex());
        joinGroupDTO.setMemberIndex(primary_member);

        System.out.println("그룹 index : " + groupDTO.getGroupIndex() + " 그룹장" + groupDTO.getGroupLeader());
    }

    public void joinGroup(MemberDTO memberDTO, GroupDTO groupDTO, JoinGroupDTO joinGroupDTO){
        int primary_member = memberDAO.selectOneById(memberDTO.getMemberID());
        int primary_group = groupDAO.selectOneByName(groupDTO.getGroupName());

        joinGroupDTO.setGroupIndex(primary_group);
        joinGroupDTO.setMemberIndex(primary_member);
        joinGroupDAO.insertJoinGroup(joinGroupDTO);

        System.out.println("그룹 가입 index : " + joinGroupDTO.getJoinGroupIndex());
    }
}

