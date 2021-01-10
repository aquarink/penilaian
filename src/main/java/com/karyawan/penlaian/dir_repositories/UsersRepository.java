package com.karyawan.penlaian.dir_repositories;

import java.util.List;

import com.karyawan.penlaian.dir_models.UsersModel;

// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UsersRepository extends CrudRepository<UsersModel, Long> {
    
    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM users WHERE password_user = :password_value LIMIT 1", nativeQuery = true)
    List<UsersModel> repoFindByPassword(@Param("password_value") String passwordUsr);

    @Modifying
    @Query(value = "SELECT * FROM USERS WHERE id <> :id_value", nativeQuery = true)
    List<UsersModel> repoFindUsersExcept(@Param("id_value") String idSessi);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET flag = :flag WHERE id = :id", nativeQuery = true)
    public void updateUserFlag(
        @Param("flag") String flagUserData,
        @Param("id") String idUserData
    );
}
