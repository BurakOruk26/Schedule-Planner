package backend;
import java.util.ArrayList;

public class Testing {
    public static void main(String[] args) {

        ArrayList<Course> c = new ArrayList<>();

        Course c1 = new Course("CS1", "Burak", 1);
        Course c2 = new Course("CS2", "Brak", 2);
        Course c3 =new Course("CS3", "Burk", 3);


        c1.addLesson( new Lesson( Lesson.EIGHT_THIRTY, Lesson.MONDAY ) );
        c1.addLesson( new Lesson( Lesson.NINE_THIRTY, Lesson.MONDAY ) );
        c1.addLesson( new Lesson( Lesson.TEN_THIRTY, Lesson.MONDAY ) );
        c1.addLesson( new Lesson( Lesson.ELEVEN_THIRTY, Lesson.MONDAY ) );
        /*
        for (Lesson lesson : c.get(0).getLessons()){
            System.out.println( lesson.getDay() + "," + lesson.getTime());
        } */

        c2.addLesson( new Lesson( Lesson.ONE_THIRTY, Lesson.TUESDAY ) );
        c2.addLesson( new Lesson( Lesson.TWO_THIRTY, Lesson.TUESDAY ) );
        c2.addLesson( new Lesson( Lesson.THREE_THIRTY, Lesson.TUESDAY ) );
        c2.addLesson( new Lesson( Lesson.FOUR_THIRTY, Lesson.TUESDAY ) );
        /*
        for (Lesson lesson : c.get(1).getLessons()){
            System.out.println( lesson.getDay() + "," + lesson.getTime());
        }*/

        c3.addLesson( new Lesson( Lesson.THREE_THIRTY, Lesson.TUESDAY ) );
        c3.addLesson( new Lesson( Lesson.FOUR_THIRTY, Lesson.TUESDAY ) );
        c3.addLesson( new Lesson( Lesson.EIGHT_THIRTY, Lesson.WEDNESDAY ) );
        c3.addLesson( new Lesson( Lesson.NINE_THIRTY, Lesson.WEDNESDAY ) );
        /*
        for (Lesson lesson : c.get(2).getLessons()){
            System.out.println( lesson.getDay() + "," + lesson.getTime());
        }*/

        c.add(c1);
        c.add(c2);
        c.add(c3);

        //
        Schedule s = new Schedule();
        s.addCourse(c);

        // there should be conflicts
        System.out.println( "*".repeat(50) );
        System.out.println( s );

        // removing second course, no conflicts
        s.removeCourse( c2 );
        System.out.println( "*".repeat(50) );
        System.out.println( s );

        // adding second course back and removing third course, no conflicts
        s.addCourse( c2 );
        s.removeCourse( c3 );
        System.out.println( "*".repeat(50) );
        System.out.println( s );

         //
    }
}
