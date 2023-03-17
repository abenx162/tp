package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.UniqueEmployeeList;

/**
 * Wraps all data at the ExecutivePro database level
 * Duplicates are not allowed (by .isSameEmployee comparison)
 */
public class ExecutiveProDb implements ReadOnlyExecutiveProDb {

    private final UniqueEmployeeList employees;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        employees = new UniqueEmployeeList();
    }

    public ExecutiveProDb() {}

    /**
     * Creates an ExecutiveProDb using the Persons in the {@code toBeCopied}
     */
    public ExecutiveProDb(ReadOnlyExecutiveProDb toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the employee list with {@code employees}.
     * {@code employees} must not contain duplicate employees.
     */
    public void setEmployees(List<Employee> employees) {
        this.employees.setEmployees(employees);
    }

    /**
     * Resets the existing data of this {@code ExecutiveProDb} with {@code newData}.
     */
    public void resetData(ReadOnlyExecutiveProDb newData) {
        requireNonNull(newData);

        setEmployees(newData.getEmployeeList());
    }

    //// employee-level operations

    /**
     * Returns true if an employee with the same identity as {@code employee} exists in the address book.
     */
    public boolean hasEmployee(Employee employee) {
        requireNonNull(employee);
        return employees.contains(employee);
    }

    /**
     * Adds an employee to the address book.
     * The employee must not already exist in the address book.
     */
    public void addEmployee(Employee p) {
        employees.add(p);
    }

    /**
     * Replaces the given employee {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The employee identity of {@code editedPerson} must not be the same as another
     * existing employee in the address book.
     */
    public void setEmployee(Employee target, Employee editedEmployee) {
        requireNonNull(editedEmployee);

        employees.setEmployee(target, editedEmployee);
    }

    /**
     * Removes {@code key} from this {@code ExecutiveProDb}.
     * {@code key} must exist in the address book.
     */
    public void removeEmployee(Employee key) {
        employees.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return employees.asUnmodifiableObservableList().size() + " employees";
        // TODO: refine later
    }

    @Override
    public ObservableList<Employee> getEmployeeList() {
        return employees.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExecutiveProDb // instanceof handles nulls
                && employees.equals(((ExecutiveProDb) other).employees));
    }

    @Override
    public int hashCode() {
        return employees.hashCode();
    }
}