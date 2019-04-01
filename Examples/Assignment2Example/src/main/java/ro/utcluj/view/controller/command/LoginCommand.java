/*************************************************************************
 * ULLINK CONFIDENTIAL INFORMATION
 * _______________________________
 *
 * All Rights Reserved.
 *
 * NOTICE: This file and its content are the property of Ullink. The
 * information included has been classified as Confidential and may
 * not be copied, modified, distributed, or otherwise disseminated, in
 * whole or part, without the express written permission of Ullink.
 ************************************************************************/
package ro.utcluj.view.controller.command;

import ro.utcluj.view.model.users.User;

public class LoginCommand implements Command<User>
{
    private final String password;
    private final String username;

    public LoginCommand(String username, String password)
    {
        this.password = password;
        this.username = username;
    }

    @Override
    public User execute()
    {
        return User.login(username, password);
    }

    @Override
    public User undo()
    {
        return null;
    }
}
