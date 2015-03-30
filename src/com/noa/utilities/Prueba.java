/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noa.utilities;

import java.util.StringTokenizer;

/**
 *
 * @author NoaD
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String str = "hdog<div>ghfhfg<<<ghhdhdfhd<div>ghfhfghfg<div>nmbmn<div>";
        String delim = "<div>";
        
        StringYokenizer syk = new StringYokenizer(str, delim);
        
        while (syk.hasMoreTokens()) {            
            System.out.println(syk.nextToken());
        }
        
        
    }
    
}
