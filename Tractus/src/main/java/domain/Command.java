/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 * Enumeration that is used in communication between objects. 
 * For example ui.KeyListener calls engine.PlayerAction with this enumeration.
 */
public enum Command {

    /**
     *
     */
    NORTH,

    /**
     *
     */
    EAST,

    /**
     *
     */
    SOUTH,

    /**
     *
     */
    WEST
}
