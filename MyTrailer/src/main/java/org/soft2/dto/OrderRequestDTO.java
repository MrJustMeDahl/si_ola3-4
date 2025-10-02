/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.soft2.dto;

import java.time.LocalDateTime;

/**
 *
 * @author kotteletfisk
 */
public class OrderRequestDTO {

    public int trailerId;
    public String userId;
    public boolean insurance;
    public LocalDateTime startTime;
    public LocalDateTime endTime;

    public OrderRequestDTO() {}

    public OrderRequestDTO(int trailerId, String userId, boolean insurance, LocalDateTime startTime, LocalDateTime endTime) {
        this.trailerId = trailerId;
        this.userId = userId;
        this.insurance = insurance;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
