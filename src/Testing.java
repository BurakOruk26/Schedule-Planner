import java.util.ArrayList;

public class Testing {
    public static void main(String[] args) {

        ArrayList<Course> c = new ArrayList<>();

        c.add (new Course("CS1", "Burak", 1));
        c.add (new Course("CS2", "Brak", 2));
        c.add (new Course("CS3", "Burk", 3));

        c.get(0).addLesson( new Lesson( Lesson.MONDAY, Lesson.EIGHT_THIRTY ) );
        c.get(0).addLesson( new Lesson( Lesson.MONDAY, Lesson.NINE_THIRTY ) );
        c.get(0).addLesson( new Lesson( Lesson.MONDAY, Lesson.TEN_THIRTY ) );
        c.get(0).addLesson( new Lesson( Lesson.MONDAY, Lesson.ELEVEN_THIRTY ) );

        c.get(1).addLesson( new Lesson( Lesson.TUESDAY, Lesson.ONE_THIRTY ) );
        c.get(1).addLesson( new Lesson( Lesson.TUESDAY, Lesson.TWO_THIRTY ) );
        c.get(1).addLesson( new Lesson( Lesson.TUESDAY, Lesson.THREE_THIRTY ) );
        c.get(1).addLesson( new Lesson( Lesson.TUESDAY, Lesson.FOUR_THIRTY ) );

        c.get(2).addLesson( new Lesson( Lesson.TUESDAY, Lesson.THREE_THIRTY ) );
        c.get(2).addLesson( new Lesson( Lesson.TUESDAY, Lesson.FOUR_THIRTY ) );
        c.get(2).addLesson( new Lesson( Lesson.WEDNESDAY, Lesson.EIGHT_THIRTY ) );
        c.get(2).addLesson( new Lesson( Lesson.WEDNESDAY, Lesson.NINE_THIRTY ) );

        Schedule s = new Schedule(c);

        System.out.println( "*".repeat(50) );

        System.out.println( s );

        System.out.println( "*".repeat(50) );

        s.removeCourse( c.get(1) );

        System.out.println( s );

        System.out.println( "*".repeat(50) );
        
        s.addCourse( c.get(1) );
        s.removeCourse( c.get(2) );

        System.out.println( s );
    }
}
