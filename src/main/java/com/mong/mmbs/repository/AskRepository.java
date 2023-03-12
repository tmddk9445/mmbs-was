package com.mong.mmbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mong.mmbs.entity.AskEntity;

@Repository
public interface AskRepository extends JpaRepository<AskEntity, Integer>{

  public List<AskEntity> findByAskWriter(String UserId);

  public AskEntity findByAskId(int askId);

  public AskEntity deleteByAskId(int askId);

  public List<AskEntity> findByAskWriterAndAskDatetimeGreaterThanEqualAndAskSortAndAskStatusOrderByAskDatetimeDesc(String userId, String askDatetime,int askStatus, int askSort);

}
