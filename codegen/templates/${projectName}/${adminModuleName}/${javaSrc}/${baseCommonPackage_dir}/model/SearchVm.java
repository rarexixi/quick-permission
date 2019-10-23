package ${baseCommonPackage}.model;

public interface SearchVm<T extends BaseCondition> {

    default T getConditionExtension() {
        return null;
    }
}
