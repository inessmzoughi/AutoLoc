package tn.esprit.autoloc.service;

public interface IGenericService<T, ID>
        extends ILectureService<T, ID>, IEcritureService<T, ID>, ISuppressionService<ID> {
}