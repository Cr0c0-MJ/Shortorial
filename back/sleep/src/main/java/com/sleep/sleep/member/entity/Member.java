package com.sleep.sleep.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sleep.sleep.shorts.entity.TryShorts;
import com.sleep.sleep.shorts.entity.UploadShorts;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@JsonIgnoreProperties(ignoreUnknown =true)
public class Member implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no", updatable = false)
    private int memberIndex;

    @Column(unique = true)
    private String memberId;
    @Column(nullable = false)
    private String memberPass;
    @Column(unique = true)
    private String memberNickname;
    @Column(nullable = false)
    private String memberProfile;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;


    public static UserDetails of(Member member){
        return Member.builder()
                .memberId(member.getMemberId())
                .memberPass(member.getMemberPass())
                .memberNickname(member.getMemberNickname())
                .memberProfile(member.getMemberProfile())
                .memberRole(member.getMemberRole())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.getMemberRole().name()));
        return authorities;
    }


    @Override
    public String getPassword() {
        return getMemberPass();
    }


    @Override
    public String getUsername() {
        return getMemberId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private String  memberTiktokLink;
    @OneToMany(fetch = FetchType.EAGER, mappedBy="tryNo")
    private List<TryShorts> tryShorts;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "uploadNo")
    private List<UploadShorts> uploadShorts;
}
