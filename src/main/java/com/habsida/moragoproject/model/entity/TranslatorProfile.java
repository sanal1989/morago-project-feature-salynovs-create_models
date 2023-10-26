package com.habsida.moragoproject.model.entity;

import com.habsida.moragoproject.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TranslatorProfile extends AbstractAuditable implements Profile {

    private String dateOfBirth;
    private String email;
    private Boolean isAvailable;
    private Boolean isOnline;
    private String levelOfKorean;
    private Boolean isActive;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    private Set<Language> languageList = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    private Set<Theme> themeList = new HashSet<>();

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    private File file;

}
