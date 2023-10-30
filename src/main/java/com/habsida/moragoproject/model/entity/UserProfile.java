package com.habsida.moragoproject.model.entity;

import com.habsida.moragoproject.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserProfile extends AbstractAuditable  {
    private Boolean isFreeCallMade;

    @Override
    public String toString() {
        return "UserProfile{" +
                "isFreeCallMade=" + isFreeCallMade +
                '}';
    }
}
