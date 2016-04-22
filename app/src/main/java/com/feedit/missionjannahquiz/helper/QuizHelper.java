package com.feedit.missionjannahquiz.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.feedit.missionjannahquiz.entity.QuizEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fikriansyah on 31/12/2015.
 */
public class QuizHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "db_quiz";
    // tasks table name
    private static final String TABLE_NAME = "tbl_mj";
    // tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_QUEST = "quest";
    private static final String KEY_ANSWER = "answer"; // correct option
    private static final String KEY_OPTA = "opta"; // option a
    private static final String KEY_OPTB = "optb"; // option b
    private static final String KEY_OPTC = "optc"; // option c
    private static final String KEY_OPTD = "optd"; // option c

    private SQLiteDatabase dbase;

    public QuizHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUEST
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT," + KEY_OPTD + " TEXT)";
        db.execSQL(sql);
        makeQuiz();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);
    }

    public void makeQuiz(){
        QuizEntity q1 = new QuizEntity("Hadiah yang paling ditunggu penduduk surga ?",
                "Bidadari", "Melihat Allah SWT", "Bertemu saudara", "Bertemu Rasulullah","Melihat Allah");
        this.addQuiz(q1);
        QuizEntity q2 = new QuizEntity("Siapakah 'Maghdubi'alaihim' (orang yang dimurkai) ?",
                "Majusi", "Nasrani", "Yahudi", "Orang-orang musyrik","Yahudi");
        this.addQuiz(q2);
        QuizEntity q3 = new QuizEntity("Berapa tahun lama dakwah nabi Nuh kepada kaummya ?",
                "850", "900", "950", "1000","950");
        this.addQuiz(q3);
        QuizEntity q4 = new QuizEntity("Surah apakah yang paling panjang dalam Al-Quran ?",
                "Al-Baqarah", "Al-Maidah", "Ali Imran", "Al-Kahfi","Al-Baqarah");
        this.addQuiz(q4);
        QuizEntity q5 = new QuizEntity("Nabi yang bukan dari kalangan Bani Israil ?",
                "Sulaiman", "Musa", "Isa", "Ibrahim","Ibrahim");
        this.addQuiz(q5);
        QuizEntity q6 = new QuizEntity("Keturunan nabi apa kaum Bani Israil ?",
                "Ya'qub", "Musa", "Ibrahim", "Sulaiman","Ya'qub");
        this.addQuiz(q6);
        // END
    }

    public void addQuiz(QuizEntity quiz){
        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUEST, quiz.getQuestion());
        values.put(KEY_ANSWER, quiz.getAnswer());
        values.put(KEY_OPTA, quiz.getOptionA());
        values.put(KEY_OPTB, quiz.getOptionB());
        values.put(KEY_OPTC, quiz.getOptionC());
        values.put(KEY_OPTD, quiz.getOptionD());

        // Inserting Row
        dbase.insert(TABLE_NAME, null, values);
    }

    public List<QuizEntity> getAllQuiz() {
        List<QuizEntity> quizList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                QuizEntity quest = new QuizEntity();
                quest.setId(cursor.getInt(0));
                quest.setQuestion(cursor.getString(1));
                quest.setAnswer(cursor.getString(2));
                quest.setOptionA(cursor.getString(3));
                quest.setOptionB(cursor.getString(4));
                quest.setOptionC(cursor.getString(5));
                quest.setOptionD(cursor.getString(6));

                quizList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quizList;
    }


}
