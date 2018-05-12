package org.sid.service;

import java.util.*;

import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.sid.form.ManagerForm;
import org.sid.form.ProfileForm;

import org.springframework.data.repository.query.Param;

public interface AccountService {
public AppUser saveUser(AppUser user);
public AppRole saveRole(AppRole role);
public void addRoleToUser(String email,String roleName);
public AppUser findUserByEmail(String email);
public long countuser();
public AppUser findByTokenRecovery(String token);
public AppUser findByCompanyName(String companyName);
public List<ProfileForm>getUsersByRole(String manegerEmail,String role);
public void recoveryPassword(String tokenRecovery,String dateExpiration,Long id);
public void updatePhoto(Long id,String photo );
public List<ManagerForm> getManagers();
public void refuseSignUp(Long id);
public void acceptSignUp(Long id);

}
