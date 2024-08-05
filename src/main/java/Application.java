import util.ApplicationContext;

public class Application {

    public static void main(String[] args) {
      //  ApplicationContext applicationContext = ApplicationContext.getInstance();
        //getAvg
        /* System.out.println(applicationContext.getSelectUnitService().getAvg(1l, 2l));
         */

        //get lessonWithScore
    /* Map<Map<String, Integer>, Double> lessonWithScore = applicationContext.getSelectUnitService().getLessonWithScore(1l, 2l);
        for (Map.Entry<Map<String, Integer>, Double> entry : lessonWithScore.entrySet()) {
            Map<String, Integer> key = entry.getKey();
            for (Map.Entry<String, Integer> integerEntry:key.entrySet()){
                System.out.print(integerEntry.getKey()+"("+integerEntry.getValue()+" unit)");
            }
            System.out.println(" -> Score= " + entry.getValue());
        }*/

        //get course in active term
        /*applicationContext.getCourseService().getCourseInCurrentTerm().forEach(System.out::println);*/
//---------------------------------------------------------------test select unit:
        //get course in active term
      /*  applicationContext.getCourseService().getCourseInCurrentTerm().forEach(System.out::println);

        Student student = applicationContext.getStudentService().findById(1l);
        Course course = applicationContext.getCourseService().findById(6l);
        applicationContext.getSelectUnitService().saveUnitSelection(student, course);*/
//sample 2 -->
      /*  int counter = 0;

        applicationContext.getCourseService().getCourseInCurrentTerm().forEach(System.out::println);

        Student student = applicationContext.getStudentService().findById(1l);
        Course course = applicationContext.getCourseService().findById(7l);

        int maxSelectUnit = applicationContext.getSelectUnitService().getMaxSelectUnit(student.getId(), course.getTerm().getId());
        while (maxSelectUnit > 0) {
            applicationContext.getSelectUnitService().saveUnitSelection(student, course);
            maxSelectUnit-=course.getLesson().getUnit();
        }*/
//----------------------------------------------------------------------------------------------------------------
        ApplicationContext.getInstance().getMainMenu().showMainMenu();

       /* HibernateValidatorConfiguration configure = Validation.byProvider(HibernateValidator.class).configure();
        ValidatorFactory validatorFactory = configure.failFast(false).buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        RegistrationForm registrationForm = new RegistrationForm("","");

        Set<ConstraintViolation<RegistrationForm>> validate = validator.validate(registrationForm);
        validate.forEach(result-> System.out.println(result));*/

    }
   /* record RegistrationForm(@NotBlank(message = "the name must be not blank") String name,
                            @NotBlank String mobileNumber){}*/
}
