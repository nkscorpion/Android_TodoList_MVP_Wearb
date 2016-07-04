package com.shadowinlife.todolist.userList;


import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

public class UserTodolistPresent extends MvpBasePresenter<UserTodolistView> {
    public void showTodoItems(int page) {

    }

    public void completeTodoItem(int todo_id) {

    }

    public void deleteTodoItem(int todo_id) {

    }

    public void addTodoitem() {

    }

    // Called when Activity gets destroyed, so cancel running background task
    public void detachView(boolean retainPresenterInstance) {
        super.detachView(retainPresenterInstance);
        if (!retainPresenterInstance) {
            //
        }
    }
}
