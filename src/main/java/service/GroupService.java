package service;

import persistence.dao.GroupDAO;
import persistence.dao.JoinGroupDAO;
import persistence.dao.LocalInfoDAO;
import persistence.dao.MemberDAO;
import persistence.dto.GroupDTO;
import persistence.dto.JoinGroupDTO;
import persistence.dto.LocalInfoDTO;
import persistence.dto.MemberDTO;

public class GroupService {
    private MemberDAO memberDAO;
    private GroupDAO groupDAO;
    private JoinGroupDAO joinGroupDAO;
    private LocalInfoDAO localInfoDAO;

    public GroupService(){
        this.memberDAO = new MemberDAO();
        this.groupDAO = new GroupDAO();
        this.joinGroupDAO = new JoinGroupDAO();
        this.localInfoDAO = new LocalInfoDAO();
    }

    public void createGroup(MemberDTO memberDTO, GroupDTO groupDTO, JoinGroupDTO joinGroupDTO, LocalInfoDTO localInfoDTO){
        String nickName = memberDAO.selectOneNickNameById(memberDTO.getMemberID());
        int primary_member = memberDAO.selectOneById(memberDTO.getMemberID());
        int primary_LocalInfo = localInfoDAO.selectID(localInfoDTO);

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
