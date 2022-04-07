package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contactedinfo.ContactedInfo;
import seedu.address.model.date.BirthDate;
import seedu.address.model.date.DocumentedDate;
import seedu.address.model.date.RecentDate;
import seedu.address.model.date.ReminderDate;
import seedu.address.model.description.Description;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.reminder.ReminderDescription;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_NUMBER_OF_DAYS = "Input number of days given is not a valid number";
    private static final Logger logger = LogsCenter.getLogger(ParserUtil.class);

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static int parseDays(String daysString) throws ParseException {
        String trimmedDaysString = daysString.trim();
        if (!StringUtil.isUnsignedInteger(trimmedDaysString)) {
            throw new ParseException(MESSAGE_INVALID_NUMBER_OF_DAYS);
        }
        return Integer.parseInt(trimmedDaysString);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses {@code String date} and {@code String description} into a {@code ContactedInfo}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code String date} or {@code String description} is invalid.
     */
    public static ContactedInfo parseContactedInfo(String date, String description) throws ParseException {
        requireNonNull(date);
        requireNonNull(description);
        String trimmedDate = date.trim();
        String trimmedDescription = description.trim();
        if (!ContactedInfo.isValidContactedInfo(trimmedDate, trimmedDescription)) {
            throw new ParseException(ContactedInfo.MESSAGE_CONSTRAINTS);
        }

        RecentDate recentDate = parseRecentDate(trimmedDate);
        Description desc = new Description(trimmedDescription);
        return new ContactedInfo(recentDate, desc);
    }

    /**
     * Parses a {@code String} date into a {@code BirthDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static BirthDate parseBirthDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!DocumentedDate.isValidDate(trimmedDate)) {
            throw new ParseException(DocumentedDate.MESSAGE_CONSTRAINTS);
        }
        BirthDate newBirthDate = BirthDate.parse(trimmedDate);
        if (newBirthDate.getDaysPassed() < 0) {
            throw new ParseException(BirthDate.MESSAGE_CONSTRAINTS);
        }
        return newBirthDate;
    }

    /**
     * Parses a {@code String} date into a {@code RecentDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static RecentDate parseRecentDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!DocumentedDate.isValidDate(trimmedDate)) {
            throw new ParseException(DocumentedDate.MESSAGE_CONSTRAINTS);
        }
        RecentDate newRecentDate = RecentDate.parse(trimmedDate);
        if (newRecentDate.getDaysPassed() < 0) {
            throw new ParseException(RecentDate.MESSAGE_CONSTRAINTS);
        }
        return newRecentDate;
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String description} into a {@code ReminderDescription}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static ReminderDescription parseReminderDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!ReminderDescription.isValidDescription(trimmedDescription)) {
            throw new ParseException(ReminderDescription.MESSAGE_CONSTRAINTS);
        }
        return new ReminderDescription(trimmedDescription);
    }

    /**
     * Parses a {@code String reminderDate} into a {@code ReminderDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code reminderDate} is invalid.
     */
    public static ReminderDate parseReminderDate(String reminderDate) throws ParseException {
        requireNonNull(reminderDate);
        String trimmedDate = reminderDate.trim();

        if (!ReminderDate.isValidDate(trimmedDate)) {
            throw new ParseException(DocumentedDate.MESSAGE_CONSTRAINTS);
        }

        ReminderDate parsedDate = ReminderDate.parse(reminderDate);

        if (parsedDate.getDaysPassed() > 0) {
            throw new ParseException(ReminderDate.MESSAGE_CONSTRAINTS);
        }

        return parsedDate;
    }

    /**
     * Parses a {@code String reminderDate} into a {@code ReminderDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param reminderDate the string to be read
     * @return the parsed {@code reminderDate} object
     * @throws ParseException if the {@code Reminder} has a {@code ReminderDate} that has passed.
     */
    public static ReminderDate parseSavedReminderDate(String reminderDate) throws ParseException {
        if (!DocumentedDate.isValidDate(reminderDate)) {
            throw new ParseException(DocumentedDate.MESSAGE_CONSTRAINTS);
        }
        try {
            ReminderDate savedReminderDate = parseReminderDate(reminderDate);
            return savedReminderDate;
        } catch (ParseException pe) {
            logger.warning("Reminder is saved with a past date in save file. "
                    + "If that is not intended, please change it!");
            ReminderDate savedReminderDate = ReminderDate.parse(reminderDate);
            return savedReminderDate;
        }

    }
}
