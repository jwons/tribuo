/*
 * Copyright (c) 2015-2020, Oracle and/or its affiliates. All rights reserved.
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

package org.tribuo.classification.libsvm;

import com.oracle.labs.mlrg.olcut.config.ArgumentException;
import com.oracle.labs.mlrg.olcut.config.ConfigurationManager;
import com.oracle.labs.mlrg.olcut.config.Options;
import com.oracle.labs.mlrg.olcut.config.UsageException;
import org.tribuo.Model;
import org.tribuo.Trainer;
import org.tribuo.classification.Label;
import org.tribuo.classification.TrainTestHelper;
import org.tribuo.classification.ensemble.ClassificationEnsembleOptions;
import org.tribuo.data.DataOptions;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Build and run a LibSVM classifier for a standard dataset.
 */
public class TrainTest {

    private static final Logger logger = Logger.getLogger(TrainTest.class.getName());

    /**
     * Command line options.
     */
    public static class TrainTestOptions implements Options {
        @Override
        public String getOptionsDescription() {
            return "Trains and tests a LibSVM model on the specified datasets.";
        }

        public DataOptions general;
        public LibSVMOptions libsvmOptions;
        public ClassificationEnsembleOptions ensembleOptions;
    }

    public static void main(String[] args) throws IOException {
        TrainTestOptions o = new TrainTestOptions();
        try (ConfigurationManager cm = new ConfigurationManager(args, o)) {
            Trainer<Label> trainer = o.libsvmOptions.getTrainer();
            trainer = o.ensembleOptions.wrapTrainer(trainer);
            Model<Label> model = TrainTestHelper.run(cm, o.general, trainer);
            if (model instanceof LibSVMClassificationModel) {
                logger.info("Used " + ((LibSVMClassificationModel) model).getNumberOfSupportVectors() + " support vectors");
            }
        } catch (UsageException e) {
            System.out.println(e.getUsage());
        } catch (ArgumentException e) {
            System.out.println("Invalid argument: " + e.getMessage());
        }
    }
}
