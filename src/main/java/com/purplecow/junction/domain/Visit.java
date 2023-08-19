package com.purplecow.junction.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@DynamicInsert
@Setter
@Table(name = "visit")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @JoinColumn(name = "event_idx")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @JoinColumn(name = "user_idx")
    private Users user;

    @ElementCollection
    @CollectionTable(name = "companyvisit", joinColumns = @JoinColumn(name = "visit_idx"))
    private List<CompanyVisit> companyVisitList;

    @Builder
    public Visit(Event event, Users user, List<CompanyVisit> companyVisitList){
        this.event=event;
        this.user=user;
        this.companyVisitList=companyVisitList;
    }
}
