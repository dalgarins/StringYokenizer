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
        strToValidate = str;
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
            int currentMaxPosicion = lstrToValidate.length();
            if (posTokens < currentMaxPosicion) {
                lstrToValidate = lstrToValidate.substring(scanToken(posTokens, lstrToValidate), currentMaxPosicion);
                result++;
            }
        }
        return result;
    }

    /**
     * Este metodo obtiene el siguiente token
     *
     * @return String que es el nuevo token
     */
    public String nextToken() {

        if (posActToken < strToValidate.length()) {
            posActToken = scanToken(0, strToValidate);
            strToValidate = strToValidate.substring(posActToken, strToValidate.length());
            return strToValidate;
        }
        return null;
    }

    /**
     * Este metodo verifica si existen mas tokens dentro de la cadena
     *
     * @return true si encuentra mas tokens
     */
    public boolean hasMoreTokens() {

        return (strToValidate.length() - posActToken > 0);
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
