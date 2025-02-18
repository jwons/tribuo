/*
 * Copyright (c) 2021, Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Interfaces and utilities for exporting Tribuo {@link org.tribuo.Model}s in
 * <a href="https://onnx.ai">ONNX</a> format.
 * <p>
 * ONNX exported models use floats where Tribuo uses doubles, this is due
 * to comparatively poor support for fp64 in ONNX deployment environments
 * as compared to fp32. In addition fp32 executes better on the various
 * accelerator backends available in
 * <a href="https://onnxruntime.ai">ONNX Runtime</a>.
 */
package org.tribuo.onnx;