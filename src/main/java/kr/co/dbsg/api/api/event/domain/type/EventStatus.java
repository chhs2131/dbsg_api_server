package kr.co.dbsg.api.api.event.domain.type;

public enum EventStatus {
//    상장예비심사청구,
//    심사청구승인,
//    심사청구탈락,
//    최초증권신고서_제출,
    CANCEL,
    DRAFTING_REPORT,
    BEFORE_FORECAST,
    NOW_FORECAST,
    BEFORE_SUBSCRIPTION,
    NOW_SUBSCRIPTION,
    BEFORE_REFUND,
    NOW_REFUND,
    BEFORE_DEBUT,
    NOW_DEBUT,
    AFTER_DEBUT
}
