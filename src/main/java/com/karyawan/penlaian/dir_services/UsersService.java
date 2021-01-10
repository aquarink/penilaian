package com.karyawan.penlaian.dir_services;

import java.util.List;

import com.karyawan.penlaian.dir_models.UsersModel;
import com.karyawan.penlaian.dir_repositories.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

  @Autowired
  private UsersRepository usersRepository;

  public List<UsersModel> serviceUserPassword(String password_sessi) {
    var repoData = (List<UsersModel>) usersRepository.repoFindByPassword(password_sessi);
    return repoData;
  }

  public List<UsersModel> serviceListUsersExcept(String password_sessi) {
    var repoData = (List<UsersModel>) usersRepository.repoFindUsersExcept(password_sessi);
    return repoData;
  }

  public void updateFlaging(String flagUser, String idUser) {
    usersRepository.updateUserFlag(flagUser, idUser);
  }

  // public List<UsersModel> getUserPassword(String mintaPssword) {
  // return usersRepository.findByPassword(mintaPssword);
  // }

  // public List<UsersModel> getAllUser() {
  // return usersRepository.findAll();
  // }

  // public void saveUser(UsersModel usersModel) {
  // usersRepository.save(usersModel);
  // }

  // public UsersModel getUserId(long id) {
  // return usersRepository.findById(id).get();
  // }

  // public void deleteUser(long id) {
  // usersRepository.deleteById(id);
  // }
}
