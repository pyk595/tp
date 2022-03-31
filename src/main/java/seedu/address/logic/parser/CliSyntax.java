package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_BIRTH_DATE = new Prefix("b/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_CONTACTED_DATE = new Prefix("d/");
    public static final Prefix PREFIX_CONTACTED_DESC = new Prefix("des/");
    public static final Prefix PREFIX_DELETE_CONTACTED_INFO = new Prefix("del/");
    public static final Prefix PREFIX_REMINDER_DESCRIPTION = new Prefix("r/");
    public static final Prefix PREFIX_REMINDER_DATE = new Prefix("rd/");
    public static final Prefix PREFIX_DELETE_REMINDER = new Prefix("del/");
}
