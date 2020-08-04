package com.example.mynotifactiondemo.common.mappingprofiles

import com.example.mynotifactiondemo.common.MappingProfileInterface
import com.example.mynotifactiondemo.data.api.dto.MyTransportationsResponseItemDto
import com.example.mynotifactiondemo.ui.cargoes.own.MyTransportationListItemModel
import java.lang.IllegalArgumentException

class MyTransportationsResponseItemDtoToMyTransportationListItemModelMappingProfile
    : MappingProfileInterface<MyTransportationsResponseItemDto, MyTransportationListItemModel> {

    override fun map(source: MyTransportationsResponseItemDto) =
        MyTransportationListItemModel(
            id = source.id,
            numberAndStatusChangeDate = "${source.number} от ${source.statusChangeTime}",
            statusText = statusToText(source.status),
            statusTextColor = statusToColor(source.status),
            cost = source.tariffWithVat.toString(),
            costWithoutVat = source.tariff.toString(),
            cityLoading = source.cityLoading,
            cityUnloading = source.cityUnloading,
            dateLoading = source.dateLoading,
            dateUnloading = source.dateUnloading,
            routeNodesCount = source.routeNodesCount
        )


    private fun statusToText(status: Int): String = when (status) {
        0 -> "Новый"
        1 -> "На подтверждении"
        2 -> "На исполнении"
        3 -> "Требуется переподписание"
        4 -> "Отклонена"
        5 -> "Ожидание документов"
        6 -> "Проблема с документами"
        7 -> "Оплата до"
        8 -> "Оплачена"
        9 -> "Устраните замечания"
        else -> throw IllegalArgumentException("Status `$status` has not matched any text")
    }

    private fun statusToColor(status: Int): Int = when (status) {
        3, 4 -> COLOR_RED
        1 -> COLOR_GRAY
        2 -> COLOR_BLUE
        else -> COLOR_GRAY
    }

    companion object {

        const val COLOR_RED = 0xffde350b.toInt()
        const val COLOR_BLUE = 0xff4c9aff.toInt()
        const val COLOR_GRAY = 0xff708096.toInt()

    }
}