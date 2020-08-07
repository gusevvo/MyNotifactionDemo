package com.example.mynotifactiondemo.common.mapping.profiles

import com.example.mynotifactiondemo.common.mapping.MappingProfileInterface
import com.example.mynotifactiondemo.data.api.dto.MyTransportationsResponseItemDto
import com.example.mynotifactiondemo.ui.cargoes.own.list.MyTransportationsListItemModel

class MyTransportationsResponseItemDtoToMyTransportationListItemModelMappingProfile
    :
    MappingProfileInterface<MyTransportationsResponseItemDto, MyTransportationsListItemModel> {

    override fun map(source: MyTransportationsResponseItemDto) =
        MyTransportationsListItemModel(
            id = source.id,
            numberAndStatusChangeDate = "${source.number} от ${source.statusChangeTime}",
            statusText = source.status.toStatusText(),
            statusTextColor = source.status.toStatusColor(),
            cost = source.tariffWithVat.toString(),
            costWithoutVat = source.tariff.toString(),
            cityLoading = source.cityLoading,
            cityUnloading = source.cityUnloading,
            dateLoading = source.dateLoading,
            dateUnloading = source.dateUnloading,
            routeNodesCount = source.routeNodesCount
        )
}