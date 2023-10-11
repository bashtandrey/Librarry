
package com.bashtan.librarry.constructor;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTest {
    private int id;
    private String login;
    private String password;
    private String userFirstName;
    private String userLastName;
    private int level;

}
