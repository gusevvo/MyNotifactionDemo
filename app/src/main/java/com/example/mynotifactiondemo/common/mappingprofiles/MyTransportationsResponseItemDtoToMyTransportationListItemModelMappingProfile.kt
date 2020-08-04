package com.example.mynotifactiondemo.common.mappingprofiles

import com.example.mynotifactiondemo.common.MappingProfileInterface
import com.example.mynotifactiondemo.data.api.dto.MyTransportationsResponseItemDto
import com.example.mynotifactiondemo.ui.cargoes.own.MyTransportationListItemModel

class MyTransportationsResponseItemDtoToMyTransportationListItemModelMappingProfile
    : MappingProfileInterface<MyTransportationsResponseItemDto, MyTransportationListItemModel> {
    override fun map(source: MyTransportationsResponseItemDto) =
        MyTransportationListItemModel(
             id = source.id,
             status = source.status,
             numberAndStatusChangeDate = "${source.number} от ${source.statusChangeTime}",
             cost = source.tariffWithVat.toString(),
             costWithoutVat = source.tariff.toString(),
             statusText = "Статус",
             cityLoading = source.cityLoading,
             cityUnloading = source.cityUnloading,
             dateLoading = source.dateLoading,
             dateUnloading = source.dateUnloading,
             routeNodesCount = source.routeNodesCount
         )
}