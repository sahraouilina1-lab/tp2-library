UC-07 – Register a Return
1. Use Case Name
Register a Return
2. Use Case ID
UC-07
3. Primary Actor
Librarian
4. Secondary Actors
Member
Mail server
5. Goal
To register the return of a borrowed book copy.
6. Preconditions
The member has borrowed a book.
The librarian is logged into the library system.
The returned copy belongs to an existing loan.
7. Postconditions
The loan is closed.
The copy status is updated.
If the copy is reserved, it is kept aside.
The member who reserved the copy is notified.
If the return is late, the member may be suspended.
8. Main Success Scenario
The member brings back the borrowed copy.
The librarian receives the copy.
The librarian identifies the loan in the system.
The system checks the loan information.
The system closes the loan.
The system updates the copy status.
The system checks whether the copy is reserved.
If the copy is reserved, the system keeps it aside.
The system notifies the member who reserved the copy.
The librarian completes the return process.
9. Extensions
3a. Late return:
The system detects that the return is late.
The extension point late return is activated.
The use case Suspend the member is executed.
7a. Copy reserved:
The system detects an existing reservation.
The copy is kept aside.
The member who reserved it is notified.
10. Special Requirements
The system must correctly update the loan status.
The system must notify the member when a reserved copy becomes available.
11. Frequency of Use
This use case occurs whenever a member returns a borrowed book.