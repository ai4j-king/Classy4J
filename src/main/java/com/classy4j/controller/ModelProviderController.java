//package com.classy4j.controller;
//
//import com.classy4j.core.model_manager.model.ModelType;
//import com.classy4j.core.model_manager.service.ModelProviderService;
//import com.classy4j.exception.AppException;
//import com.classy4j.model.Account;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/workspaces/current/model-providers")
//public class ModelProviderController {
//
//    @Autowired
//    private ModelProviderService modelProviderService;
//
//    @GetMapping
//    public ResponseEntity<?> getProviderList(
//            @AuthenticationPrincipal Account currentUser,
//            @RequestParam(required = false) String modelType
//    ) {
//        var providers = modelProviderService.getProviderList(
//                currentUser.getCurrentTenantId(),
//                modelType != null ? ModelType.valueOf(modelType) : null
//        );
//        return ResponseEntity.ok(Map.of("data", providers));
//    }
//
//    @GetMapping("/{provider}/credentials")
//    public ResponseEntity<?> getProviderCredentials(
//            @AuthenticationPrincipal Account currentUser,
//            @PathVariable String provider
//    ) {
//        var credentials = modelProviderService.getProviderCredentials(
//                currentUser.getCurrentTenantId(),
//                provider
//        );
//        return ResponseEntity.ok(Map.of("credentials", credentials));
//    }
//
//    @PostMapping("/{provider}/credentials/validate")
//    public ResponseEntity<?> validateProviderCredentials(
//            @AuthenticationPrincipal Account currentUser,
//            @PathVariable String provider,
//            @RequestBody Map<String, Object> credentials
//    ) {
//        try {
//            modelProviderService.validateProviderCredentials(
//                    currentUser.getCurrentTenantId(),
//                    provider,
//                    credentials
//            );
//            return ResponseEntity.ok(Map.of("result", "success"));
//        } catch (AppException e) {
//            return ResponseEntity
//                    .status(HttpStatus.BAD_REQUEST)
//                    .body(Map.of(
//                            "result", "error",
//                            "error", e.getMessage()
//                    ));
//        }
//    }
//
//    @PostMapping("/{provider}")
//    public ResponseEntity<?> saveProviderCredentials(
//            @AuthenticationPrincipal Account currentUser,
//            @PathVariable String provider,
//            @RequestBody Map<String, Object> credentials
//    ) {
////        if (!currentUser.isAdminOrOwner()) {
////            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
////        }
//
//        try {
//            modelProviderService.saveProviderCredentials(
//                    currentUser.getCurrentTenantId(),
//                    provider,
//                    credentials
//            );
//            return ResponseEntity
//                    .status(HttpStatus.CREATED)
//                    .body(Map.of("result", "success"));
//        } catch (AppException e) {
//            return ResponseEntity
//                    .status(HttpStatus.BAD_REQUEST)
//                    .body(Map.of("error", e.getMessage()));
//        }
//    }
//
//    @DeleteMapping("/{provider}")
//    public ResponseEntity<?> removeProviderCredentials(
//            @AuthenticationPrincipal Account currentUser,
//            @PathVariable String provider
//    ) {
////        if (!currentUser.isAdminOrOwner()) {
////            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
////        }
//
//        modelProviderService.removeProviderCredentials(
//                currentUser.getCurrentTenantId(),
//                provider
//        );
//        return ResponseEntity
//                .status(HttpStatus.NO_CONTENT)
//                .body(Map.of("result", "success"));
//    }
//}