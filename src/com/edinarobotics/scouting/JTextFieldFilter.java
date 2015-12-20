package com.edinarobotics.scouting;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldFilter extends PlainDocument {

    private static final long serialVersionUID = 1L;
    private int limit;
    public static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALPHA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMERIC = "0123456789";
    public static final String FLOAT = "0123456789.";
    public static final String ALPHA_NUMERIC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    protected String acceptedChars = null;
    protected boolean negativeAccepted = false;

    public JTextFieldFilter(String acceptedchars, int limit) {
        this.acceptedChars = acceptedchars;
        this.limit = limit;
    }

    public void setNegativeAccepted(boolean negativeaccepted) {
        if ((this.acceptedChars.equals("0123456789")) || (this.acceptedChars.equals("0123456789."))
                || (this.acceptedChars.equals("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"))) {
            this.negativeAccepted = negativeaccepted;
            this.acceptedChars += "-";
        }
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;

        if (this.acceptedChars.equals("ABCDEFGHIJKLMNOPQRSTUVWXYZ"))
            str = str.toUpperCase();
        else if (this.acceptedChars.equals("abcdefghijklmnopqrstuvwxyz")) {
            str = str.toLowerCase();
        }

        for (int i = 0; i < str.length(); i++) {
            if (this.acceptedChars.indexOf(String.valueOf(str.charAt(i))) == -1) {
                return;
            }
        }

        if (((this.acceptedChars.equals("0123456789."))
                || ((this.acceptedChars.equals("0123456789.-")) && (this.negativeAccepted))) && (str.indexOf(".") != -1)
                && (getText(0, getLength()).indexOf(".") != -1)) {
            return;
        }

        if ((this.negativeAccepted) && (str.indexOf("-") != -1) && ((str.indexOf("-") != 0) || (offset != 0))) {
            return;
        }

        if (getLength() + str.length() <= this.limit)
            super.insertString(offset, str, attr);
    }

}
