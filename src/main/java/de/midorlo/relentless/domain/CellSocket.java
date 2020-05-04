//package de.midorlo.relentless.domain;
//
//import lombok.Data;
//import lombok.extern.java.Log;
//
//import javax.persistence.*;
//
///**
// * Typed Value holder for Cells in Gear.
// */
//@Data
//@Log
//@Entity
//public class CellSocket {
//
//    @Id
//    private Long id;
//
//    @Basic(optional = false)
//    private String parentName;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private CellType type;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Cell cell;
//}
