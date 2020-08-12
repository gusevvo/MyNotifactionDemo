package com.example.mynotifactiondemo.common.mapping.profiles

import com.example.mynotifactiondemo.common.mapping.MappingProfileInterface
import com.example.mynotifactiondemo.data.api.dto.MyTransportationsResponseItemDto
import com.example.mynotifactiondemo.ui.main.cargoes.own.list.MyTransportationsListItemModel

class MyTransportationsResponseItemDtoToMyTransportationListItemModelMappingProfile
    :
    MappingProfileInterface<MyTransportationsResponseItemDto, MyTransportationsListItemModel> {

    override fun map(source: MyTransportationsResponseItemDto) =
        MyTransportationsListItemModel(
            id = source.id,
            numberAndStatusChangeDate = "${source.number} от ${Format.Date(source.statusChangeTime)}",
            statusText = source.status.toStatusText(),
            statusTextColor = source.status.toStatusColor(),
            cost = Format.Currency(source.tariffWithVat),
            costWithoutVat = Format.Currency(source.tariff),
            cityLoading = source.cityLoading,
            cityUnloading = source.cityUnloading,
            dateLoading = Format.Date(source.dateLoading),
            dateUnloading = Format.Date(source.dateUnloading),
            routeNodesCount = source.routeNodesCount
        )
}