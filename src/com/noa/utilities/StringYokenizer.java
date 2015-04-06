package com.noa.utilities;

import java.util.Enumeration;

/**
 *
 * @author NoaD
 */
public class StringYokenizer implements Enumeration<Object> {

    private String str;
    private String delim;
    private int maxPosition;
    private int posTokens;
    private int posActToken;
    private int posInStr;
    private String strToValidate;

    /**
     * Contructor de la clase, esta clase sirve para manejar token por palabras
     * completas
     *
     * @param str es la String que se desea evaluar
     * @param delim es el delimitador del cual dependen los tokens pueden ser
     * palabras
     *
     */
    public StringYokenizer(String str, String delim) {

        this.str = str;
        this.delim = delim;
        maxPosition = str.length();
        posActToken = 0;
        posTokens = 0;
        posInStr = 0;
        strToValidate = str.concat(delim);//para solucionar el problema del ultimo token
    }

    /**
     * Este metodo escanea la cadena en busca de tokens
     *
     * @param startPos posicion desde la que se quiere escanear los tokens
     * @param str cadena que se evaluara
     * @return int con la posicion despues del token
     */
    private int scanToken(int startPos, String str) {

        String lstr = str;
        int currentMaxPosicion = lstr.length();
        if (startPos < currentMaxPosicion) {
            lstr = lstr.substring(startPos, currentMaxPosicion);
            if (lstr.contains(delim)) {
                return lstr.indexOf(delim) + delim.length();
            }
        }
        return -1;
    }

    /**
     * Este metodo sirve para contar los tokens que tiene la cadena
     *
     * @return int con la cantidad de tokens
     */
    public int countTokens() {

        String lstrToValidate = strToValidate;
        int result = 0;
        while (lstrToValidate.contains(delim)) {
            lstrToValidate = lstrToValidate.substring(scanToken(0, lstrToValidate), lstrToValidate.length());
            result++;
        }
        return result;
    }

    /**
     * Este metodo obtiene el siguiente token
     *
     * @return String que es el nuevo token
     */
    public String nextToken() {

        int posToken;
        String response;

        posToken = scanToken(posTokens, strToValidate);
        response = strToValidate.substring(0, posToken - delim.length());
        strToValidate = strToValidate.substring(posToken);
        
        return response;
    }

    /**
     * Elimina los tokens vacios de la cadena
     */
    private void cleanEmptyTokens() {

        if (strToValidate.length() >= delim.length()) {
            if (strToValidate.subSequence(0, delim.length()).equals(delim)) {
                strToValidate = strToValidate.substring(delim.length());
                cleanEmptyTokens();
            }
        }
    }

    /**
     * Este metodo verifica si existen mas tokens dentro de la cadena
     *
     * @return true si encuentra mas tokens
     */
    public boolean hasMoreTokens() {

        cleanEmptyTokens();
        return (strToValidate.contains(delim) && !strToValidate.equals(delim));
    }

    /**
     * Este metodo verifica si existen mas elementos o tokens en la cadena
     *
     * @return true si encuentra mas tokens
     */
    @Override
    public boolean hasMoreElements() {

        return hasMoreTokens();
    }

    /**
     * Este metodo obtiene el siguiente token
     *
     * @return Object del siguiente token
     */
    @Override
    public Object nextElement() {

        return nextToken();
    }
}
