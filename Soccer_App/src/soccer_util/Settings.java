package soccer_util;

public class Settings {
    // مدة الشوط الواحد بالدقائق
    public static final int HALF_TIME_MINUTES = 45;

    // مدة المباراة بالكامل
    public static final int FULL_TIME_MINUTES = 90;

    // عدد اللاعبين الأساسيين في الفريق
    public static final int STARTING_PLAYERS = 11;

    // عدد البدلاء المسموح بهم في القائمة
    public static final int SUBSTITUTE_PLAYERS = 9;

    // الحد الأقصى لعدد التبديلات المسموح بها أثناء المباراة
    public static final int MAX_SUBSTITUTIONS = 5;

    // عدد فواصل التبديل (stoppages) المسموح بها
    public static final int MAX_SUBSTITUTION_WINDOWS = 3;

    // لا يوجد حد أقصى للأهداف — Integer.MAX_VALUE معناها مفتوح
    public static final int MAX_GOALS_PER_TEAM = Integer.MAX_VALUE;

    // الحد الأدنى والأقصى لعدد الفرق في الدوري
    public static final int MIN_TEAMS = 3;
    public static final int MAX_TEAMS = 30;

    private Settings() {}
}
