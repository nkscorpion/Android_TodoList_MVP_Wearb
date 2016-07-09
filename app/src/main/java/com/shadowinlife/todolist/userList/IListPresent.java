package com.shadowinlife.todolist.userList;

import com.shadowinlife.todolist.models.Entity.Todo;

/**
 * Created by shadowinlife on 16/7/6.
 */
public interface IListPresent {
    /**
     * Refresh data in view layer
     */
    void refreshSession();

    /**
     * Action when the user click on Fab button
     */
    void onAddTodoButtonClick();

    /**
     * Action when the user click to edit on To.Do item
     *
     * @param todo
     */
    void onClickTodoItemToEdit(Todo todo);

    /**
     * Action when the user long click on To.Do item
     *
     * @param todo
     */
    void onLongClickTodoItem(Todo todo);

    /**
     * Update To.Do completed property
     *
     * @param todo
     * @param completed
     * @param position
     */
    void updateTodoIsCompleted(Todo todo, boolean completed, int position);

    /**
     * Delete old To.Do
     *
     * @param todo
     */
    void delete(Todo todo);
}
