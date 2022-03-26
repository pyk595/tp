package seedu.address.model.tag;

import java.util.List;

/**
 * Unmodifiable view of {@code UniqueTagList} that stores a list of unique tags.
 */
public interface ReadOnlyUniqueTagList {

    /**
     * Returns an unmodifiable view of the tags list.
     * This list will not contain any duplicate tags.
     *
     * @return the tags list.
     */
    List<Tag> getUniqueTagList();

    /**
     * Returns the size of {@code UniqueTagList}.
     *
     * @return size of {@code UniqueTagList}.
     */
    int getUniqueTagListSize();
}
