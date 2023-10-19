package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.model.entity.PasswordReset;
import com.habsida.moragoproject.model.input.PasswordResetInput;
import com.habsida.moragoproject.repository.PasswordResetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class PasswordResetService {

    PasswordResetRepository passwordResetRepository;

    public PasswordResetService(PasswordResetRepository passwordResetRepository) {
        this.passwordResetRepository = passwordResetRepository;
    }

    public List<PasswordReset> findAll(){
        return passwordResetRepository.findAll();
    }

    public PasswordReset findById(Long id){
        return passwordResetRepository.findById(id)
                .orElseThrow(()->new NotFoundByIdException("PasswordReset -> PasswordReset doesn't find by Id " + id));
    }

    public PasswordReset createPasswordReset(PasswordResetInput passwordResetInput){
        PasswordReset passwordReset = new PasswordReset();
        if(!isNull(passwordResetInput.getPhone()) && !passwordResetInput.getPhone().isEmpty()){
            passwordReset.setPhone(passwordResetInput.getPhone());
        }
        if(!isNull(passwordResetInput.getToken()) && !passwordResetInput.getToken().isEmpty()){
            passwordReset.setToken(passwordResetInput.getToken());
        }
        if(!isNull(passwordResetInput.getResetCode())){
            passwordReset.setResetCode(passwordResetInput.getResetCode());
        }
        return passwordResetRepository.save(passwordReset);
    }

    public String deletePasswordResetById(Long id){
        try{
            passwordResetRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundByIdException(e.getMessage());
        }
        return "PasswordReset with Id "+id+" deleted";
    }

    public PasswordReset updatePasswordReset(Long id, PasswordResetInput passwordResetInput){
        PasswordReset passwordReset =passwordResetRepository.findById(id).get();
        if(!isNull(passwordResetInput.getPhone()) && !passwordResetInput.getPhone().isEmpty()){
            passwordReset.setPhone(passwordResetInput.getPhone());
        }
        if(!isNull(passwordResetInput.getToken()) && !passwordResetInput.getToken().isEmpty()){
            passwordReset.setToken(passwordResetInput.getToken());
        }
        if(!isNull(passwordResetInput.getResetCode())){
            passwordReset.setResetCode(passwordResetInput.getResetCode());
        }else {
            passwordReset.setResetCode(0);
        }
        return passwordResetRepository.save(passwordReset);
    }
}
