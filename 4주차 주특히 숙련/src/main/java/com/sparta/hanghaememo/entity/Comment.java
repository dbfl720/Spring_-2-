package com.sparta.hanghaememo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.hanghaememo.dto.CommentDto;
import com.sparta.hanghaememo.entity.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @JsonIgnore  //??@JsonIgnore: Response에 해당 필드가 제외된다
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)   //?
    @JoinColumn(name = "Memo_Id")   //외래 키 매핑 시 사용 (name = 매핑할 외래키 이름)
    private Memo memo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;





    @Column(nullable = false)
    private String commentContents;

//    @Column(nullable = false)
//    private String commentUsername;


    public Comment (CommentDto commentDto, User user, Memo memo) {  //
        this.commentContents = commentDto.getCommentContents();
        this.memo = memo;
        this.user = user;
    }

    public void update(CommentDto commentDto) {
        this.commentContents = commentDto.getCommentContents();
    }
}