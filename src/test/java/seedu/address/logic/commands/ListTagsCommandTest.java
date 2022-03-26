package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ListTagsCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.PersonBuilder;

public class ListTagsCommandTest {

    private Model model;
    private Model expectedModel;

    @Test
    public void execute_tagsExist_success() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        String expectedMessage = String.format(MESSAGE_SUCCESS, model.getUniqueTagList());
        assertCommandSuccess(new ListTagsCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noPerson_noTagMessage() {
        model = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        assertCommandSuccess(new ListTagsCommand(), model, ListTagsCommand.MESSAGE_NO_TAGS, expectedModel);
    }

    @Test
    public void execute_noTags_noTagMessage() {
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson(new PersonBuilder().build());
        model = new ModelManager(addressBook, new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        assertCommandSuccess(new ListTagsCommand(), model, ListTagsCommand.MESSAGE_NO_TAGS, expectedModel);
    }
}
