package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.contactedinfo.ContactedInfo;
import seedu.address.model.date.RecentDate;
import seedu.address.model.description.Description;

/**
 * Jackson-friendly version of {@link ContactedInfo}.
 */
public class JsonAdaptedContactedInfo {
    private final String description;
    private final String date;

    /**
     * Constructs a {@code JsonAdaptedContactedInfo} with the given {@code description} and {@code date}.
     *
     */
    @JsonCreator
    public JsonAdaptedContactedInfo(@JsonProperty("date") String date,
            @JsonProperty("description") String description) {
        this.date = date;
        this.description = description;
    }

    /**
     * Converts a given {@code ContactedInfo} into this class for Jackson use.
     */
    public JsonAdaptedContactedInfo(ContactedInfo source) {
        date = source.getRecentDate().value;
        description = source.getDescription().value;
    }

    /**
     * Converts this Jackson-friendly adapted contacted info object into the model's {@code ContactedInfo} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted contacted info.
     */
    public ContactedInfo toModelType() throws IllegalValueException {
        if (!ContactedInfo.isValidContactedInfo(date, description)) {
            throw new IllegalValueException(ContactedInfo.MESSAGE_CONSTRAINTS);
        }

        RecentDate recentDate = RecentDate.parse(date);
        Description desc = new Description(description);
        return new ContactedInfo(recentDate, desc);
    }
}
