package com.traincompany.management.admin_inputs_api.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Group_Roles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Group_Role_ID")
    private Integer id;

    @Column(name = "Group_ID", insertable = false, updatable = false)
    private Integer groupId;

    @Column(name = "Role_ID", insertable = false, updatable = false)
    private Integer roleId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="Group_ID")
    private Group group;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="Role_ID")
    private Role role;
}
