package org.sid.dao;

import java.util.List;

import org.sid.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long>{


@Query("select c from Candidate c where c.email=:x and c.appUser.email=:y")
public Candidate getCandidatByemail (@Param("x")String x,@Param("y")String y);


@Query("select c from Candidate c where c.appUser.email like :email and c.title like :title and c.firstName like :firstname and c.lastName like :lastname ")
public Page<Candidate> getCandidats(@Param("firstname")String firstname,@Param("lastname")String lastname
		,@Param("title")String title,@Param("email")String email,Pageable pageable);



@Query("select c from Candidate c where c.candidateId=:id ")
public Candidate findBycandidateId(@Param("id")Long id);



@Query("select distinct(c.title) from Candidate c where c.appUser.email like :y ")
public List <String> findTitles(@Param("y") String y);

@Modifying(clearAutomatically = true)
@Query("UPDATE Candidate c SET c.photo =:photo   WHERE c.candidateId=:id")
public void updatePhotoCandidate(@Param("photo") String photo,@Param("id")Long id );
}




