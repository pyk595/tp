package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS_WITH_BIRTHDAY_TODAY;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;



class BirthdayCommandTest {

    private static final DateTimeFormatter FORMATTER_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String MESSAGE_SUCCESS = String.format(BirthdayCommand.MESSAGE_SUCCESS, 0);
    private Model model;
    private Model emptyModel;
    private Person alice;
    private Person bob;
    private Person carl;


    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), new UserPrefs());
        emptyModel = new ModelManager(new AddressBook(), new UserPrefs());
        alice = ALICE;
        bob = BOB;
        carl = CARL;
        LocalDate today = LocalDate.now();
        String todayDate = today.format(FORMATTER_INPUT);
        LocalDate yesterday = today.minusDays(1);
        String yesterdayDate = yesterday.format(FORMATTER_INPUT);
        LocalDate tenYearsAgo = today.minusYears(10);
        String tenYearsAgoDate = tenYearsAgo.format(FORMATTER_INPUT);
        alice = new PersonBuilder(alice).withBirthDate(todayDate).build();
        bob = new PersonBuilder(bob).withBirthDate(yesterdayDate).build();
        carl = new PersonBuilder(carl).withBirthDate(tenYearsAgoDate).build();

    }

    @Test
    public void execute_listHasNoPersons_showsEmptyList() {
        assertCommandSuccess(new BirthdayCommand(), model, MESSAGE_SUCCESS, emptyModel);
    }

    @Test
    public void execute_listHasNoBirthdaysToday_showsEmptyList() {
        model.addPerson(bob);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS_WITH_BIRTHDAY_TODAY);
        assertEquals(0, model.getFilteredPersonListSize());
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        assertEquals(1, model.getFilteredPersonListSize());
    }

    @Test
    public void execute_listHasBirthdayToday_showsOne() {
        model.addPerson(alice);
        model.addPerson(bob);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS_WITH_BIRTHDAY_TODAY);
        assertEquals(1, model.getFilteredPersonListSize());
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        assertEquals(2, model.getFilteredPersonListSize());
    }

    @Test
    public void execute_listHasBirthDateTenYearsAgo_showsOne() {
        model.addPerson(carl);
        model.addPerson(bob);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS_WITH_BIRTHDAY_TODAY);
        assertEquals(1, model.getFilteredPersonListSize());
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        assertEquals(2, model.getFilteredPersonListSize());
    }


}
