package seedu.address.model.contactedinfo;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.date.DocumentedDate;
import seedu.address.model.date.RecentDate;
import seedu.address.model.description.Description;

/**
 * Represents a Person's recently contacted information in the address book.
 */
public class ContactedInfo implements Comparable<ContactedInfo> {

    public static final String MESSAGE_CONSTRAINTS =
            "Only 1 date and description should be given, both fields are needed as well, "
            + RecentDate.MESSAGE_CONSTRAINTS + " and " + Description.MESSAGE_CONSTRAINTS;

    private final Description description;
    private final RecentDate recentDate;

    /**
     * Constructs an {@code ContactedInfo}.
     *
     * @param recentDate A valid description.
     * @param description a valid date.
     */
    public ContactedInfo(RecentDate recentDate, Description description) {
        requireNonNull(recentDate);
        requireNonNull(description);
        checkArgument(isValidContactedInfo(recentDate.value, description.value), MESSAGE_CONSTRAINTS);
        this.description = description;
        this.recentDate = recentDate;
    }

    /**
     * Returns the default contacted info.
     *
     * @return the default contacted info.
     */
    public static ContactedInfo getDefaultContactedInfo() {
        return new ContactedInfo(RecentDate.defaultRecentDate(), Description.defaultDesc());
    }

    /**
     * Returns true if a given string is a valid contacted information.
     *
     * @param dateTest date string to test.
     * @param descriptionTest description to test.
     * @return true if a given string is a valid contacted information.
     */
    public static boolean isValidContactedInfo(String dateTest, String descriptionTest) {
        return DocumentedDate.isValidDate(dateTest) && Description.isValidDescription(descriptionTest);
    }

    /**
     * Returns {@code RecentDate} instance.
     *
     * @return {@code RecentDate} instance.
     */
    public RecentDate getRecentDate() {
        return recentDate;
    }

    /**
     * Returns {@code Description} instance.
     *
     * @return {@code Description} instance.
     */
    public Description getDescription() {
        return description;
    }

    /**
     * returns contacted information in String format.
     *
     * @return contacted information in String format.
     */
    @Override
    public String toString() {
        return "Last contacted: " + description.toString()
                + " (" + recentDate.toString() + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactedInfo // instanceof handles nulls
                && recentDate.equals(((ContactedInfo) other).recentDate)
                && description.equals(((ContactedInfo) other).description)); // state check
    }

    @Override
    public int compareTo(ContactedInfo o) {
        return recentDate.compareTo(o.recentDate);
    }

    @Override
    public int hashCode() {
        return description.hashCode() + recentDate.hashCode();
    }
}
