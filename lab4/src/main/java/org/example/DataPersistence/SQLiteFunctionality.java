package org.example.DataPersistence;

import java.util.ArrayList;
import java.util.HashMap;

public class SQLiteFunctionality implements DatabaseFunctionality{
    private final SQLiteQuery sqliteQuery;
    public SQLiteFunctionality(String databaseName){
        sqliteQuery = new SQLiteQuery(databaseName);
    }
    public static boolean doesDatabaseExist(String databaseName){
        return SQLiteQuery.doesExist(databaseName);
    }
    public String[] trainingNames(){
        return sqliteQuery.SelectTrainingNames();
    }
    public boolean addNewTraining(String name){
            return sqliteQuery.insertNewTrainingName(name);
    }
    public void deleteTrainingName(String name){
        sqliteQuery.deleteTrainingName(name);
    }
    public boolean doesTrainingNameExist(String name){
        return sqliteQuery.doesTrainingNameExist(name);
    }
    public boolean doesExerciseNameExist(String exerciseName, String trainingName){
        return sqliteQuery.doesExerciseNameExist(exerciseName, trainingName);
    }
    public boolean changeTrainingName(String oldName, String newName){
        return sqliteQuery.updateTrainingName(oldName, newName);
    }
    public ArrayList<ArrayList<String>> listExercises(String trainingName){
        return sqliteQuery.selectExercises(trainingName);
    }
    public void addExercise(HashMap<String, String> hashMap, String trainingName){
        sqliteQuery.addExercise(hashMap, trainingName);
    }
    public void modifyExercise(HashMap<String, String> hashMap, String trainingName, String oldName){
        sqliteQuery.modifyExercise(hashMap, trainingName, oldName);
    }
    public void deleteExercise(String trainingName, String exerciseName){
        sqliteQuery.deleteExercise(trainingName, exerciseName);
    }
    public ArrayList<ArrayList<String>> selectSessions(){
        return sqliteQuery.selectSessions();
    }
    public boolean addSession(String trainingName, String date, String time){
        return sqliteQuery.addSession(trainingName, date, time);
    }
    public boolean modifySession(String newValue, String column, String date){
        return sqliteQuery.modifySession(newValue, column, date);
    }
    public void deleteSession(String date){
        sqliteQuery.deleteSession(date);
    }
    public ArrayList<ArrayList<String>> selectGoals(){
        return sqliteQuery.selectGoals();
    }
    public void deleteGoal(String exerciseName){
        sqliteQuery.deleteGoal(exerciseName);
    }
    public String[] listAllExercises(){
        return sqliteQuery.listAllExercises();
    }
    public boolean addGoal(String[] data){
        return sqliteQuery.addGoal(data);
    }
    public void updateGoal(String[] data, String exercise){
        sqliteQuery.updateGoal(data, exercise);
    }
    public ArrayList<ArrayList<String>> selectExerciseStats(String exerciseName){
        return sqliteQuery.selectExerciseStats(exerciseName);
    }
}
