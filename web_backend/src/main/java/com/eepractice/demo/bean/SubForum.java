package com.eepractice.demo.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;





@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubForum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( name = "title")
    private String title;

    @Column(name = "date")
    private Date date;

    @Column( name = "chairmanId")
    private Integer chairManId;

    @Column( name = "secretary" )
    private Integer secretaryId;

    @ManyToMany(cascade = CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinTable(name = "ForumMember", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "subforum_id", referencedColumnName = "id") })
    @JsonIgnore
    private List<User> members;

}
